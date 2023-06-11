using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IBloodTypeRepository : IRepositoryAsync<BloodType>
    {
        void Update(BloodType bloodType);
    }
}