using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface IProfessionRepository : IRepositoryAsync<Profession>
    {
        void Update(Profession profession);
    }
}