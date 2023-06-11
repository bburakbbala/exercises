using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IMedicineRepository : IRepositoryAsync<Medicine>
    {
        void Update(Medicine medicine);
    }
}